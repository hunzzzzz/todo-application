package org.hunzz.todoapplication.global.exception

class ModelNotFoundException(modelName: String) :
        RuntimeException("Model '$modelName' not found with given id.")