package system.chitietngaynghi.clients;

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
import system.chitietngaynghi.dto.ChiTietNgayNghiDTO;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChiTietNgayNghiClient {

    @Autowired
    @Qualifier("chiTietNgayNghiRest")
    public RestTemplate restTemplate;

    @Autowired
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper;

    private final String path = "";

    public ChiTietNgayNghiDTO getCTNgayNghi(Integer id) {

        String restURL = path + "/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<ChiTietNgayNghiDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<ChiTietNgayNghiDTO>() {
                });

        return responseEntity.getBody();
    }

    public List<ChiTietNgayNghiDTO> getCTNgayNghis() throws IOException {

        String restURL = path;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);

        try {
            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<ChiTietNgayNghiDTO>>() {});
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addCTNgayNghi(ChiTietNgayNghiDTO chiTietNgayNghiDTO) {

        String restURL = path + "/add";

        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<ChiTietNgayNghiDTO> entity = new HttpEntity<>(chiTietNgayNghiDTO, headers);

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