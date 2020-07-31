# duedatecalculator

After launching application you can call the defined rest endpoints on http://localhost:8080/swagger-ui.html under issue-tracker-controller.

with the POST request to /issue endpoint you can create new issues, submit timestamp is LocalDateTime.now(), application counts the resolve timestamp from that, then adds back a response.

with the GET request to /all_issues you can list all submitted issues.

with the GET request to /all_issues/{id} you can get the calculated resolve time of a specific issue. (id stands for the uuid of the already reported issue)

(So if timestamp is between 5PM - 9AM app will save nothing.)
