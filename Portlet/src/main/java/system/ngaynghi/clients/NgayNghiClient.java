package system.ngaynghi.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import system.chiphiphatsinh.dto.ChiPhiPhatSinhDTO;
import system.ngaynghi.dto.NgayNghiDTO;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NgayNghiClient {

    @Autowired
    @Qualifier("ngayNghiRest")
    public RestTemplate restTemplate;

    @Autowired
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper;

    private final String path = "";

    public List<NgayNghiDTO> getNgayNghis() throws IOException {

        String restURL = path;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);

        try {
            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<NgayNghiDTO>>() {});
        } catch (Exception e) {
            return null;
        }
    }
    public NgayNghiDTO get(Integer id) {

        String restURL = path + "/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<NgayNghiDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<NgayNghiDTO>() {
                });

        return responseEntity.getBody();
    }
    public boolean add(NgayNghiDTO ngayNghiDTO) {

        String restURL = path + "/add";

        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<NgayNghiDTO> entity = new HttpEntity<>(ngayNghiDTO, headers);

        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
                entity, String.class, new Object[0]);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();

    }

    public boolean delete(Integer id) {

        String restURL = path + "/delete/" + id;

        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE,
                entity, String.class, new Object[0]);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();

    }
}