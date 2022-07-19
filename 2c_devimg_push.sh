source ./0_env_gcp.sh

gcloud auth print-access-token  --impersonate-service-account $DEV_SA  | podman login -u oauth2accesstoken --password-stdin us-central1-docker.pkg.dev
podman push $TAG_ORG
podman push $TAG_GCP

