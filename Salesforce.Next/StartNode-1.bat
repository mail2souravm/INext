c:
cd C:\Users\mail2\git\INext\Salesforce.Next
java -Dwebdriver.ie.driver=chromedriver.exe -jar selenium-server-standalone-3.11.0.jar -port 4445 -role node -hub http://localhost:4444/grid/register -browser "browserName=chrome,version=65,platform=WINDOWS,maxInstances=10,maxSession=10"
PAUSE
