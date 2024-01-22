package org.hunzz.todoapplication.global.annotation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import org.hunzz.todoapplication.domain.member.model.RoleValidator
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [RoleValidator::class])
annotation class ValidRole(
    val message: String = "",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val enumClass: KClass<out Enum<*>>
)
