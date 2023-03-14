package com.github.jvalentino.juliet.rest

import com.github.jvalentino.juliet.dto.DashboardDto
import com.github.jvalentino.juliet.dto.HomeDto
import com.github.jvalentino.juliet.dto.LoginDto
import com.github.jvalentino.juliet.service.AuthService
import com.github.jvalentino.juliet.service.BffService
import com.github.jvalentino.juliet.user.model.UserDto
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpSession

/**
 * Generic services for the BFF
 */
@CompileDynamic
@Slf4j
@RestController
class BffRest {

    @Autowired
    BffService bffService

    @Autowired
    AuthService authService

    @Autowired
    AuthenticationManager authenticationManager

    @GetMapping('/')
    @CircuitBreaker(name = 'Index')
    HomeDto index() {
        bffService.index()
    }

    @GetMapping('/dashboard')
    @CircuitBreaker(name = 'Dashboard')
    DashboardDto dashboard() {
        DashboardDto result = new DashboardDto()
        result.with {
            documents = bffService.allDocs()
        }
        result
    }

    @PostMapping('/custom-login')
    @CircuitBreaker(name = 'CustomLogin')
    LoginDto login(@RequestBody UserDto user, HttpSession session) {
        LoginDto result = authService.login(user, authenticationManager, session)
        result
    }

    /*
    @PostMapping('/upload-file')
    ResultDto upload(@RequestParam('file') MultipartFile file) {
        AuthUser user = userService.currentLoggedInUser()
        docService.uploadNewDoc(user, file, DateGenerator.date())

        new ResultDto()
    }

    @GetMapping('/view-versions/{docId}')
    ViewVersionDto index(@PathVariable(value='docId') Long docId) {
        ViewVersionDto result = new ViewVersionDto()
        result.with {
            doc = docService.retrieveDocVersions(docId)
        }

        log.info("Doc ${docId} has ${result.doc.versions.size()} versions")

        result
    }

    // https://www.baeldung.com/servlet-download-file
    @GetMapping('/version/download/{docVersionId}')
    void downloadVersion(@PathVariable(value='docVersionId') Long docVersionId, HttpServletResponse response) {
        DocVersion version = docService.retrieveVersion(docVersionId)

        response.setContentType(version.doc.mimeType)
        response.setHeader('Content-disposition',
                "attachment; filename=${version.doc.name.replaceAll(' ', '')}")

        InputStream is = new ByteArrayInputStream(version.data)
        OutputStream out = response.getOutputStream()

        byte[] buffer = new byte[1048]

        int numBytesRead
        while ((numBytesRead = is.read(buffer)) > 0) {
            out.write(buffer, 0, numBytesRead)
        }
    }

    @PostMapping('/version/new/{docId}')
    ResultDto upload(@RequestParam('file') MultipartFile file, @PathVariable(value='docId') Long docId) {
        AuthUser user = userService.currentLoggedInUser()

        docService.uploadNewVersion(user, file, DateGenerator.date(), docId)

        new ResultDto()
    }
     */

}
