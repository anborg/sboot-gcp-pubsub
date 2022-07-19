source ./0_env_gcp.sh
gcloud run services describe $APP_NAME --region=$GCP_REGION --format=export
