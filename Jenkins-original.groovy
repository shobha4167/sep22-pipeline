pipeline{
    agent any
    stages{
        stage("checkout"){
            steps{
                echo "clone the source code"
            }
        }
        stage("Build"){
            steps{
                echo "here we build source code"
            }
        }
        stage("upload artifacts"){
            steps{
                echo "upload artifacts to s3"
            }
        }
    }
}