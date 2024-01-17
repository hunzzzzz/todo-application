package org.hunzz.todoapplication.global.exception

class WrongCriteriaException(criteria: String) :
    RuntimeException("Criteria '$criteria' is not suitable.")