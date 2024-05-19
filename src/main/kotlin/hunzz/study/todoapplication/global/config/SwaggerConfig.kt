package hunzz.study.todoapplication.global.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun api(): OpenAPI = OpenAPI()
        .info(
            Info()
                .title("TODO Application API")
                .description("TODO Application API schema")
                .version("1.0.0")
        )
}