source ./0_env_gcp.sh

#
gcloud run deploy $APP_NAME --image=$TAG_GCP \
  --service-account=$CRUN_SA\
  --project $GCP_PROJECT --region=$GCP_REGION \
  --memory=1G --cpu=1 --allow-unauthenticated  --ingress=internal \
  --min-instances=0 --max-instances=1 \
  --vpc-egress=all-traffic --vpc-connector=$VPC_CONNECTOR

#  --set-secrets DATABSE_URL="projects/50811623506/secrets/SBOOTR2DBC_DB_URL"
#  --set-env-vars "DATABSE_URL=r2dbc:h2:file:///./mydb.h2:///mydb1;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;DATABASE_TO_UPPER=false;CASE_INSENSITIVE_IDENTIFIERS=TRUE;MODE=MYSQL"
