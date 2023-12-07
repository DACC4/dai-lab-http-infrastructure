# Static website
## Docker
### Build :
```bash
docker build -t dai_http_static:latest .
```

### Run :
```bash
docker run -p 80:80 dai_http_static:latest
```

## NGINX Configuration Documentation

### `user nobody;`
- **Description:** Specifies the user under which the NGINX worker processes will run.

### `events { }`
- **Description:** Block for configuring connection processing and related parameters.

### `http { }`
- **Description:** Block containing configurations for HTTP server settings.

#### `include mime.types;`
- **Description:** Includes the `mime.types` file, which helps in mapping file extensions to MIME types.
- **Purpose:** Ensures proper MIME type handling for various file types, which is particularly useful for CSS files and other resources.

#### `sendfile on;`
- **Description:** Enables the use of the `sendfile()` system call for serving files.
- **Purpose:** Increases file transfer efficiency by directly sending files from disk to network without copying them to userspace.

#### `server { }`
- **Description:** Configuration block for a specific server.

##### `listen 80;`
- **Description:** Instructs NGINX to listen for HTTP connections on port 80.
- **Purpose:** Specifies the default HTTP port for incoming connections.

##### `listen [::]:80;`
- **Description:** Similar to `listen 80;` but for IPv6 connections.
- **Purpose:** Listens for incoming connections on IPv6 addresses on port 80.

##### `server_name localhost;`
- **Description:** Defines the server name for this virtual server block.
- **Purpose:** Associates requests with this server block based on the provided server name.

##### `root /var/www/html;`
- **Description:** Sets the root directory for serving files for this server block.
- **Purpose:** Specifies the base directory from which files will be served for requests to this server block.

##### `charset utf-8;`
- **Description:** Sets the character encoding for the server block to UTF-8.
- **Purpose:** Ensures proper interpretation of characters in the UTF-8 character set.

##### `location / { }`
- **Description:** Defines the configuration for requests to the root URL (`/`).
- **Purpose:** This location block can be used to specify further directives for handling requests to the root URL.

**Note:** The `location / { }` block is currently empty and doesn't contain any specific configurations for handling requests to the root URL. Additional directives for handling specific URLs or requests can be added within this block.
