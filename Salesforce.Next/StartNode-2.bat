c:
cd C:\Users\mail2\git\INext\Salesforce.Next
java -Dwebdriver.gecko.driver=geckodriver.exe -jar selenium-server-standalone-3.11.0.jar -port 4446 -role node -hub http://localhost:4444/grid/register -browser "browserName=firefox,version=59,platform=WINDOWS,maxInstances=10,maxSession=10"
PAUSE
