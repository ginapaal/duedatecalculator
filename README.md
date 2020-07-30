# duedatecalculator

After launching application you can call rest endpoints on http://localhost:8080/swagger-ui.html under issue-tracker-controller.

with the POST request to /issue endpoint you can create new issues, submit timestamp is LocalDateTime.now(), application counts the resolve timestamp from that, then adds back a response.

with the GET request to /all_issues you can list all submitted issues.

(So if timestamp is between 5PM - 9AM app will save nothing.)
