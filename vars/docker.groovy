def call(String credentialsId, String imageName ){

    withCredentials(
        [
            usernamePassword(
                credentialsId: "${credentialsId}",
                passwordVariable: "password",
                usernameVariable: "username",
            )
        ]
    ){
        sh "docker login -u ${username} -p ${password}"
        sh "docker build -t ${username}/${imageName}:latest ."
        sh "docker push ${username}/${imageName}:latest"
    }

    sh "docker compose up -d --build app"
}
