# Spring Boot JMS Example

Steps to install ActiveMQ

1. Install ActiveMQ - ActiveMq will download in ZIP folder and extract it
2. Create a Brocker - Go to the path where ZIP folder Unzipped. C:\Users\Prakash\softwares\ActiveMQ\apache-artemis-2.11.0\bin
            Exectue following command to create a brocker -  # artemis create /{path where we want to create a brocker}/{Brocker name}
3. RUN the Brocker - go to the brocker path. Example - C:\Users\Prakash\softwares\ActiveMQ\Mybroker\bin
            Execute following command to run the brocker -  # artemis run


Once the brocker up and running, we can run the code, spring boot will pick the activemq brocker automatically.
