package org.hunzz.todoapplication.global.exception

class InvalidCriteriaException(criteria: String) :
    RuntimeException("Criteria '$criteria' is not suitable.")