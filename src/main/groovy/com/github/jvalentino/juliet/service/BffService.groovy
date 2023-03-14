package com.github.jvalentino.juliet.service

import com.github.jvalentino.juliet.doc.api.DocRestApi
import com.github.jvalentino.juliet.doc.model.Doc
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * General service class for supporting the REST endpoints
 */
@CompileDynamic
@Slf4j
@Service
class BffService {

    @Autowired
    DocRestApi docRestApi

    List<Doc> allDocs() {
        docRestApi.dashboard().documents
    }

}
