// <!--
// Employee Management WebSocket Application with MongoDB

// Objective:
// ----------
// Your task is to develop a WebSocket-based Employee Management System using Node.js and MongoDB. 
// The system should allow multiple clients to interact with a database to perform the following operations:
// 	1. Insert Employee Records (INSERT <name> <salary> <role> <department> <experience>)
// 	2. Retrieve Employee List (RETRIEVE)
// 	3. Retrieve Employee List who belongs to a department (RETRIEVE_BY_DEPT <department>)
	
// The WebSocket server should be capable of handling multiple concurrent clients and persist employee data in MongoDB.


// // MongoDB Employee Schema
// const employeeSchema = new mongoose.Schema({
//     name: String,
//     salary: Number,
//     role: String,
//     department: String,
//     experience: Number
// });

// Requirements:
// -------------
// Implement WebSocket Server
// 	The server should:
// 		-> Accept multiple client connections. (give a response as "Connected" )
// 		-> Process incoming commands from clients as discussed above.
// 		-> Log each received command on the console.
// 		-> Ensure proper error handling (e.g., invalid salary, missing name, etc.).
		
// Expected Behavior
// -----------------

// ============================================================================================
// Client Command			                Server Response
// ============================================================================================
// INSERT Alice 50000 Developer IT 5	    "Employee inserted successfully."
// INSERT Bob 60000 Manager IT 5	        "Employee inserted successfully."

// RETRIEVE				                "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
//                                         "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"

// RETRIEVE_BY_DEPT IT                     "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
//                                         "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"


// INVALID					                "Invalid command."
// ============================================================================================

// Note: 
// -> Your implementation must use MongoDB for data persistence.
// -> The server should run on port 8080.
// -> The system should allow multiple clients to connect.


// EXAMPLE URL value=>   ws://10.11.xx.xx:8080

// -->
// <config>
//     <url value=""></url>
// </config>

const WebSocket = require('ws');
const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/employeeDB',{
    useNewUrlParser: true,
    useUnifiedTopology: true

}).then(() => console.log("MongoDB Connected"))
.catch(err => console.error("MongoDB Connection Error:", err));

const employeeSchema = new mongoose.Schema({
    id:Number,
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});

const Employee = mongoose.model('Employee', employeeSchema);
let employeeCounter = 1;
const wss = new  WebSocket.Server({port:8080},()=>{
    console.log('WebSocket Server is running on ws://localhost:8080');
})

wss.on('connection', ws =>{
    ws.on('message', async message => {
        console.log("Received :", message.toString());
        const args = message.toString().split(' ');
        const command = args[0];
        try {
            if(command == 'INSERT' && args.length == 6){
                const[_, name, salary, role, department, experience] = args;
                if( isNaN(salary) || isNaN(experience) ){
                    return ws.send("Invalid salary or experience format");
                }
                const count = await Employee.countDocuments();
                const newEmployee = new Employee({ id: count + 1, name, salary: Number(salary), role, department, experience: Number(experience) });
                await newEmployee.save();
                ws.send(`ID: ${count+1}`);
            }
            else if (command === 'RETRIEVE') {
            const employees = await Employee.find();
                const response = employees.map(emp => `ID: ${emp.id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`).join('\n');
                ws.send(response || "No employees found.");
            }else if (command === 'RETRIEVE_BY_DEPT' && args.length === 2) {
                console.log("re dept")
                const department = args[1];
                const employees = await Employee.find({ department });
                const response = employees.map(emp => `ID: ${emp.id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`).join('\n');
                console.log(response);
                ws.send(response || "No employees found in this department.");
            }else {
                ws.send("Invalid command.");
            }
        } catch (error) {
            console.error("Error processing command:", error);
            ws.send("Server error.");
        }

    });
})