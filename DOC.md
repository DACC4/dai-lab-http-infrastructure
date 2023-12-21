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
