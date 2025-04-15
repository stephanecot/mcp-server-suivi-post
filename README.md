This project is an example about how to build a MCP server using Spring AI.

It's currently build using Spring AI 1.0.0-M7, which is a milestone version. The final version will be released in the next weeks.

## Prerequisites

- Java 21
- Spring Boot 3.4.x
- Maven 3.x
- Docker to build it (or another container engine like Podman)


## Build

To build the project, run the following command:

```mvn clean install```

then, we need to build the docker image:

```cd docker```

```build.sh``` or ```build.cmd```

It will build a docker image named `scottin/mcp-suivi-suivi-post` with the tag `latest`.

Then, you can use this image in a MCP client application like Claude Desktop or VS Code 1.99+.


## Integration with Claude Desktop

To use MCP servers with Claude Desktop, you need to install the MCP plugin. You can find the installation instructions here:
https://modelcontextprotocol.io/quickstart/user

You have to edit the Claude Desktop configuration file `claude_desktop_config.json` to add the MCP server:

```
{
  "mcpServers": {
	"mcp-suivi-suivi-post": {
	    "command": "podman",
	    "args": ["run", "-i", "--rm", "scottin/mcp-suivi-suivi-post"]
        }
   }
}
```

Note: Don't forget to quit and restart Claude Desktop to apply the changes.

You will the avaibles tools, provided by the MCP servers, in the "Tools" menu of Claude Desktop.

![alt text](https://github.com/stephanecot/mcp-server-suivi-post/blob/main/img/claude1.png?raw=true)


Then, you could ask a question through the chat prompt, and the MCP server(s) will be called with the right tools to provide the answer:

![alt text](https://github.com/stephanecot/mcp-server-suivi-post/blob/main/img/claude2.png?raw=true)
