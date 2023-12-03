package com.seonjuleee.msa.itemservice.valid;

import com.seonjuleee.msa.itemservice.constant.ItemType;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = ItemTypeValid.ItemTypeValidator.class) // 유효성 체크 시 어떤 valid 클래스로 처리할지
@Documented // JavaDoc에서 설명 추가
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER}) // 어노테이션 대상 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 메모리 시점 지정
public @interface ItemTypeValid {
    String message() default "허용되지 않은 물품 유형입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class ItemTypeValidator implements ConstraintValidator<ItemTypeValid, String> {
        @Override
        public boolean isValid(String code, ConstraintValidatorContext context) {
            boolean hasItemType = false;
            ItemType[] itemTypes = ItemType.values();
            for (ItemType it : itemTypes) {
                hasItemType = it.hasItemCode(code);
                if (hasItemType) break;
            }

            return hasItemType;
        }
    }
}
