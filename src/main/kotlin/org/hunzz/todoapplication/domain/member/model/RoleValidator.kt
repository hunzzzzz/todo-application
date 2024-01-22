package org.hunzz.todoapplication.domain.member.model

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.hunzz.todoapplication.global.annotation.ValidRole

class RoleValidator : ConstraintValidator<ValidRole, Any> {
    private lateinit var enumValues: Array<out Enum<*>>

    override fun initialize(annotion: ValidRole) {
        enumValues = annotion.enumClass.java.enumConstants
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext?) =
        enumValues.any { it.name == value.toString() }
}