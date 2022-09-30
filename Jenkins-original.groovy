pipeline{
    agent any
    stages{
        stage("checkout"){
            steps{
                echo "clone the source code"
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/shobha4167/Sep22-code.git']]])
                sh "ls -la"
                       } 
        }
        stage("Build"){
            steps{
                echo "here we build source code"
                sh ''' mvn clean package
                    ls -ls target/
                    '''
            }
        }
        stage("upload artifacts"){
            steps{
                echo "upload artifacts to aws s3"
                sh '''
                aws s3 cp target/hello-*.war s3://sep22222/$JOB_NAME/master/$BUILD_NUMBER/
                '''
            }
        }
        stage("check artifacts"){
            steps{
                echo "check artifacts"
                sh "aws s3 ls s3://sep22222/$JOB_NAME/master/$BUILD_NUMBER/"
            }
        }
    }
}