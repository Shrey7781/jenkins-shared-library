#!/usr/bin/env groovy
def call(String imageName){
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        echo "DEBUG - imageName received: ${imageName}"
        sh "docker build -t ${imageName} ."
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh "docker push ${imageName}"
}
}
