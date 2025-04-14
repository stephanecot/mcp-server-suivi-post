
package fr.scottin.mcp.server;

import fr.scottin.mcp.server.tools.McpServerSuiviPostTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpServerSuiviPostApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerSuiviPostApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider suiviColisToolsProvider(McpServerSuiviPostTools suiviColisTools) {
        return MethodToolCallbackProvider.builder().toolObjects(suiviColisTools).build();
    }
}
