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
  * $ docker run *image_number* 
