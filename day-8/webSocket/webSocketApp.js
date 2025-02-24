/*
 <!--
Employee Management WebSocket Application

Objective:
-----------
Your task is to develop a WebSocket-based Employee Management System using Node.js. 
This system will allow clients to:
    1. Insert Employee Records (INSERT <name> <salary>)
    2. Retrieve Employee List (RETRIEVE)
    3. Handle Invalid Commands (e.g., INVALID should return "Invalid command")
Your goal is to implement and test a WebSocket-based server and client, 
ensuring that all operations work correctly.

Requirements:
-------------
1. Implement WebSocket Server
	The server should:
		-> Accept multiple client connections.
		-> Process client messages and handle commands:
			1. INSERT <name> <salary> → Adds an employee to an in-memory array.
			2. RETRIEVE → Returns all stored employees.
			3. Any other command should return "Invalid command."
		-> Maintain an in-memory array of employees (no database required).
		-> Log each received command on the console.
		
		
Expected Behavior
-----------------

============================================================================================
Client Command			Server Response
============================================================================================
INSERT Alice 50000		"Employee inserted successfully."
INSERT Bob 60000		"Employee inserted successfully."
RETRIEVE				"ID: 1, Name: Alice, Salary: 50000"
                        "ID: 2, Name: Bob, Salary: 60000"
INVALID					"Invalid command."
============================================================================================

Note: 
-> The server should run on port 8080.
-> The system should allow multiple clients to connect.


EXAMPLE URL value=>   ws://10.11.xx.xx:8080
-->
<config>
    <url value=""></url>
</config>
 */

const WebSocket = require('ws');

const wss =new  WebSocket.Server({port:8080});
const database = [];
let idcount = 1;
wss.on('connection',(ws)=>{
	ws.on('message',(message)=>{
		const command = message.toString();
		const arr = command.split(" ");
		if( arr[0] === "INSERT" && arr.length ===3 ){
			// console.log('Insert');
			const name = arr[1];
			const salary = parseInt(arr[2]);
			database.push({id:idcount++ ,name:name, salary:salary });
			// console.log(database);
			ws.send('Employee inserted successfully.');
		}
		else if( arr[0] === "RETRIEVE" ){
			if( database.length === 0){
				ws.send('no employees found')
			}
			// console.log('retrive');
			database.forEach((data)=>{
				ws.send(`ID: ${data.id}, Name: ${data.name}, Salary: ${data.salary}`);
			})
		}else{
			ws.send('Invalid command.');
		}
	})
})