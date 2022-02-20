package system.duan.clients;

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
import system.duan.dto.DuAnDTO;
import system.ot.dto.OTDTO;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DuAnClient {

    private final String path = "";

    @Autowired
    @Qualifier("duAnRest")
    public RestTemplate restTemplate;

    @Autowired
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper;

    public DuAnDTO getDuAn(Integer id) {

        String restURL = path + "/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<DuAnDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<DuAnDTO>() {
                });

        return responseEntity.getBody();
    }

    public List<DuAnDTO> getDuANs() throws IOException {

        String restURL = path;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);

        try {

            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<DuAnDTO>>() {
            });
        } catch (Exception e) {

            return null;
        }
    }

    public List<DuAnDTO> getByNhanVien(Integer id) throws IOException {

        String restURL = path + "/nhanvien/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);
        try {

            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<DuAnDTO>>() {
            });
        } catch (Exception e) {

            return null;
        }
    }

    public boolean addDuAn(DuAnDTO duAnDTO) {

        String restURL = path + "/add";
        HttpHeaders headers = new HttpHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<DuAnDTO> entity = new HttpEntity<>(duAnDTO, headers);

        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
                entity, String.class);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }

    public boolean delete(Integer id) {

        String restURL = path + "/delete/" + id;
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, entity, String.class);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();

    }
    public List<DuAnDTO> getDuAnByUserId(Long userId) {
        String restURL = path + "/nhanvienuser/" + userId;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);

        try {

            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<DuAnDTO>>() {
            });
        } catch (Exception e) {

            return null;
        }
    }
}
