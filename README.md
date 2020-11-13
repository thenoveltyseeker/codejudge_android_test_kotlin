# android-kotlin-app

IMPORTANT NOTES:

    1. The backend endpoint host url is mentioned in app/src/main/res/raw/config.properties. The property name is "api_url". An example is mentioned in "MainActivity.kt".
    2. PLEASE USE THIS PROPERTY ("api_url") WHEN YOU ARE TRYING TO CALL A BACKEND API. ALSO DON'T CHANGE THIS PROPERTY ELSE THE APP WILL NOT BUILD PROPERLY AND YOUR SUBMISSION WILL NOT BE SCORED.
    3. Make sure you follow the steps mentioned under "PROJECT START STEPS" and ensure that the steps execute successfully.
    4. Make sure you follow the steps mentioned under "SERVER BACKEND DOCKER START STEPS" and ensure that the steps execute successfully.

PROJECT START STEPS:

    Pre-requisites:
    1. Install java (https://www.java.com/en/download/).
    2. Install Android Studio/Eclipse


SERVER BACKEND DOCKER START STEPS:

    Pre-requisites:
    1. Docker is installed (http://console.codejudge.io/setup)

    Steps:
    1. Run the mentioned image (test/question) in a container (Make sure port 8080 is available):
        1.a. Run the following command(s) in the terminal/command line:
            - docker run -i -p8080:8080 android-backend
        1.b. Check the logs for any errors.

    2. Go to http://localhost:8080 in your browser to view it.

DOCKER STOP STEPS:

    Steps:
    1. Run the following command(s):
        - docker ps
    2. Copy the container id and run the below command:
        - docker stop <container_id>
        - docker system prune

DOCKER LOGS:

    Steps:
    1. Run the following command(s):
        - docker ps
    2. Copy the container id and run the below command:
        - docker logs <container_id>

DOCKER REMOVE CONTAINER:

    Steps:
    1. Run the following command(s):
        - docker ps
    2. Copy the container id and run the below command:
        - docker rm <container_id>
        - docker system prune

DOCKER REMOVE IMAGE:

    Steps:
    1. Run the following command(s):
        - docker ps
    2. Copy the image id and run the below command:
        - docker rmi <image_id>
        - docker system prune
