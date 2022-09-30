pipeline {
    agent {
        label "mvnslave"
    }
    stages{
        stage ('Hello'){
            steps {
                echo 'Hello World'
                sh "hostname -i"
            }
        }
    }
}