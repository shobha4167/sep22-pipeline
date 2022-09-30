//Declarative pipeline
pipeline {
    agent any
    stages{
        stage("Build"){
            steps{
                echo "here we are build a code"
            }
        }
        stage("Upload artifacts"){
            steps{
                echo "upload artifacts to s3"
            }
        }
        stage("download artifacts"){
            steps{
                echo "Download artifacts"
            }
        }
        stage("Deploy artifacts"){
            steps{
                echo "here we deploy artifacts"
            }
        }

    }
}