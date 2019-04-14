# LaboSMTP
### Brief Description
This project is a client application (TCP). The client will connect to an SMTP server using a SMTP protocol. It uses config files to determine every parameters of the application.

### Mock Server Setup Instructions
These are the instructions to setup a mock server using docker and MockMock:
* Go into the docker folder of this repo.
* Make sure docker is installed, then execute the following command:
  * $ docker build ./
* After the image is built, you can see the image number after the message "Successfully built". Copy this number.
* Now run the image using:
  * $ docker run -p 25:25 -p 8282:8282 *image_number* 
 
These steps build a docker image on a new container. When the server is started, it copies the MockMock's jar and execute it.
 
### Instructions For Running a Prank Campaign

### Most Important Classes
##### ConfigManager.java
The first class we chose to speak about is *ConfigManager.java*. This class is used to parse the config files and get the input we want using these.
We chose to implement this class as a singleton, because there is no reason why we would want two instances of ConfigManager.
There is 3 private method that may be difficult to understand.
The first one, *parseAddress* is a simple parser that read each line of the *address.txt* file and store each line (being an address) as a Person that has this address.
The second one is *parseMessages*. This method follow a similar pattern, using the *message.txt* file to store the first line of each entry in the text file as the subject of a mail, then taking the rest as the body. Each entry must be separated with "*---*" in the text file.
The last one, *parseConfig* uses the Properties package in java to parse specific field that we want our application to know.

##### PrankGenerator.java
This class is used to generate a prank. It does so by generating a group by selecting victims among the all the victims parsed by the config manager and selecting a random from all available messages.

