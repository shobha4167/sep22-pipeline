pipeline{
    agent any
    parameters{
        string (name:'SOURCE_BRANCH',defaultValue:'master',description:'provide source code branch')
        string (name:'ServerIP',description:"provide your server IP's")
        string (name:'BUILD_JOB_NAME',description:'')
        string (name:'MY_BUILD_NUMBER',description:'')
    }
    stages{
        stage("clone artifacts"){
            steps{
                echo "cloning artifacts to jenkins local"
                sh '''
                aws s3 cp s3://sep22222/$BUILD_JOB_NAME/$SOURCE_BRANCH/$MY_BUILD_NUMBER/hello-$MY_BUILD_NUMBER.war .
                '''
            }
        }
        stage("Deploy"){
            steps{
                sh '''
                IFS=',' read -ra MYIPARRAY <<< $ServerIP
                
                for myIP in ${MYIPARRAY[@]}
                   do
                           echo "my IP is :$myIP"
                           ssh -i /tmp/sep22.pem ec2-user@${myIP} "hostname"
                           scp -o StrictHostKeyChecking=no -i /tmp/sep22.pem hello-$MY_BUILD_NUMBER.war ec2-user@${myIP}:/var/lib/tomcat/webapps/
                done
                    '''
            }
        }
    }
}