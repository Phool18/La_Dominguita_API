package la_dominga.controlador;


import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.Picture;
import la_dominga.servidor.PictureService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("foto")
public class PictureController {
    private PictureService service;

    public PictureController(PictureService service) {
        this.service = service;
    }

    @GetMapping
    public RespuestaServidor list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public RespuestaServidor find(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        return service.downloadByFileName(fileName, request);
    }

    @PostMapping
    public RespuestaServidor save(@ModelAttribute Picture obj) {
        return service.save(obj);
    }

    public RespuestaServidor update(Long aLong, Picture obj) {
        return null;
    }

    public RespuestaServidor delete(Long aLong) {
        return null;
    }
}