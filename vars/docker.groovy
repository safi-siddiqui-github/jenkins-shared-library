def call(String credentialsId, String imageName, String imageTag ){

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
        sh "docker build -t ${username}/${imageName}:${imageTag} ."
        sh "docker push ${username}/${imageName}:${imageTag}"
    }

    sh "docker compose up -d --build app"
}
