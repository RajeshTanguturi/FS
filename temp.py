import sys
import requests
import json
from pypdf import PdfMerger, PdfWriter
import os
import re




def combine_pdfs_in_a_directory(directory):

	pdf_paths = os.listdir(directory)

	for i in range(len(pdf_paths)):
		pdf_paths[i] = os.path.join(directory, pdf_paths[i])

	to_save_as = os.path.join(directory, " ".join(f"{directory.split(os.path.sep)[-1]} - All Topics Combined.pdf".split(" - ", maxsplit=1)))

	print(f"Saving at: {to_save_as}")

	merger = PdfWriter()

	for pdf in pdf_paths:
		merger.append(pdf)

	merger.write(to_save_as)
	merger.close()
	



def test():

	# create_directory_structure()

	# subject = input("Enter subject (dbms, os): ").upper()
	# unit = input("Enter unit (I, II, III, IV, V): ").upper()
	# unit_id = input("Enter unit id: ")
	
	subject = sys.argv[1].upper()
	unit = sys.argv[2].upper()
	unit_id = sys.argv[3]


	# subject = "OS"
	# unit = "III"
	# unit_id = 299

	print(f"Received:\n\tSubject - {subject}\n\tUnit - {unit}\n\tTopic Id - {unit_id}")



	unit = f"Unit - {unit}"


	false = False
	null = None
	true = True

	response = requests.get(fr"https://api.tesseractonline.com/studentmaster/get-topics-unit/{unit_id}", headers={"Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlcnJvciI6ZmFsc2UsInVzZXJuYW1lIjoiMjJCRDFBNjYxVCIsInN1YiI6NjgzLCJyb2xlIjoiU1RVREVOVCIsImNvbGxlZ2VJZCI6MSwiY29sbGVnZU5hbWUiOiJLTUlUIiwibmFtZSI6IlRBTkdVVFVSSSBSQUpFU0giLCJpYXQiOjE3NDAzNzE0MTYsImV4cCI6MTc0MDM5MzAxNn0.RJ1bpArFm0RrgYQw0PMx16i3gIwJBn8kQVII2D9nTvA", "Accept": "/"}).json()
# "User-Agent": "Thunder Client (https://www.thunderclient.com)"
# }).json()

	# print(response)


	for idx, topic in enumerate(response["payload"]["topics"], start=1):
		topic_name = " ".join(re.findall(r"[A-Za-z]+", topic["name"]))
		pdf_url =  f"https://api.tesseractonline.com/{topic['pdf']}"

		# print(f"Topic {idx} - {topic_name}: {pdf_url}")

		# file_to_save = os.path.join(r"C:\Users\sram\Desktop\Tranberg USB\USB Drive\Aditya new\Aditya\CSM A\II Year\II Semester", subject, unit, f"Topic {idx} - {topic_name}.pdf")
		file_to_save = os.path.join(os.getcwd(), subject, unit, f"Topic {idx} - {topic_name}.pdf")




		# print(file_to_save)

		need_to_merge_pdfs = False

		if(not os.path.exists(file_to_save)):

			need_to_merge_pdfs = True

			blob = requests.get(pdf_url).content

			with open(file_to_save, "wb") as f:
				f.write(blob)

				print(f"Saved: {file_to_save}")

		else:
				print(f"Already exists: {file_to_save}")

	if(need_to_merge_pdfs):
		combine_pdfs_in_a_directory(os.path.join(os.getcwd(), subject, unit))


if(len(sys.argv) != 4):
	print("Usage: python test.py <Subject> <Unit (I - V)> <TopicId>")
	exit(0)

test()



