package uz.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import uz.project.aspects.ExceptionHandlerAdvice;

@Configuration
@EnableAspectJAutoProxy
public class AopConfiguration {
}
