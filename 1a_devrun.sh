#export http_proxy=http://internet.ford.com:83
#export https_proxy=http://internet.ford.com:83
#export no_proxy="ford.com,localhost,127.0.0.1"
#export HTTP_PROXY=http://internet.ford.com:83
#export HTTPS_PROXY=http://internet.ford.com:83
#export NO_PROXY="ford.com,localhost,127.0.0.1"
export GOOGLE_APPLICATION_CREDENTIALS=$HOME/.config/gcloud/application_default_credentials.json
mvn spring-boot:run
#gradle bootRun