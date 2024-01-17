# Documentation

## Traefik
### Implementation and Configuration Details
Traefik is implemented as a reverse proxy in this Docker environment. It is responsible for routing traffic to various backend services, specified by the rules in the service labels. The `reverse_proxy` service uses the Traefik v2.10 Docker image. Traefik is configured to:

- Listen to Docker events, facilitating dynamic response to changes in the Docker environment.
- Route traffic for different services:
  - `static_app` is built from `./static_web/` and receives traffic intended for the host `localhost`.
  - `todo_api` is built from `./todo_api/` and handles requests to `localhost` with a path prefix of `/api`. Its load balancer server port is set to 7070, and a middleware strips the `/api` prefix before requests reach the service.

### Why Using a Reverse Proxy is Useful to Improve the Security of the Infrastructure
A reverse proxy like Traefik enhances security in several ways:
- It provides a single point of entry into the infrastructure, reducing the attack surface.
- SSL/TLS termination at the proxy level ensures encrypted traffic within the internal network, safeguarding sensitive data.
- Traefik can also handle authentication and access control, adding an extra layer of security.
- It enables centralized logging and monitoring of traffic, which is crucial for detecting and responding to security incidents.

### Accessing the Traefik Dashboard and How It Works
The Traefik dashboard is a powerful tool for monitoring and managing the reverse proxy configuration. It can be accessed in this setup as follows:
- Navigate to `http://localhost:8080` in a web browser.
- The dashboard is enabled and made accessible through the `--api.insecure=true` command line argument, allowing for easy access without SSL/TLS setup.

The dashboard provides:
- Real-time monitoring of routed services and their health status.
- Visualization of the request routing and the configuration of middlewares, services, and routers.
- Ability to observe dynamic changes in the environment as Traefik automatically updates its configuration in response to Docker events.

### Scaling instances using Traefik
Start the docker in detached mode to easily run commands:
`docker compose up -d`
Use the scale argument to specify how many instances you want to run of each service:
`docker compose scale todo_api=3 static_app=2`
If the value is less than the current number of instances, the extra instances will be stopped. If the value is greater than the current number of instances, new instances will be created.

### Load Balancing
#### Sticky Sessions
Sticky sessions are enabled for the `todo_api` service. This ensures that requests from the same client are always routed to the same instance of the service. This is useful for maintaining session state, for example, when a user is logged in to a web application. To test this, start the docker in detached mode to easily run commands and scale up the `todo_api` service:
`docker compose up -d`
`docker compose scale todo_api=3`
Then, navigate to `http://localhost/api/todos` in a web browser and observe the logs in the console. Refresh the page a few times and notice that the requests are always routed to the same instance of the service. Clear the cookie and reload, it will be routed to a different instance. Refresh a few times and notice that the requests are always routed to the same instance of the service.

#### Round Robin
Round robin load balancing is enabled for the `static_app` service. This ensures that requests are distributed evenly across all instances of the service. To test this, start the docker in detached mode to easily run commands and scale up the `static_app` service:
`docker compose up -d`
`docker compose scale static_app=2`
Then, navigate to `http://localhost` in a web browser and observe the logs in the console. Refresh the page a few times and notice that the requests are routed to different instances of the service.

### HTTPS 
A self-signed certificate is used for HTTPS. This is not recommended for production environments, but it is sufficient for testing purposes. The certificate pem files have been uploaded to traefik. 

Two config files have been created: `traefik.yml` and `dyn.yml`. The `traefik.yml` file contains the static traefik configuration and the entry points definition. The `dyn.yml` file contains the dynamic traefik configuration, including the middlewares and the TLS certificate configuration.

The entry point `web` listens on port 80, and redirects traffic to the entry point `websecure` on port 443. The `websecure` entry point is configured to use the certificate pem files. Both the 'static_app' and 'todo_api' services are configured to use the `websecure` entry point.
