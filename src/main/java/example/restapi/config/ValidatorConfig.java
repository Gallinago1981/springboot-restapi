package example.restapi.config;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Bean Validation で利用するエラーメッセージをValidationMessageから変更する
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator() {
        Validator validator =
                Validation.byDefaultProvider()
                        .configure()
                        .messageInterpolator(
                                new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("message/message"))
                        )
                        .buildValidatorFactory()
                        .getValidator();
        return validator;
    }

}
