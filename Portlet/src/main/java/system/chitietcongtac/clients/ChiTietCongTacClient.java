package system.chitietcongtac.clients;

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
import system.chitietcongtac.dto.ChiTietCongTacDTO;
import system.congtac.dto.CongTacDTO;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChiTietCongTacClient {

    @Autowired
    @Qualifier("chiTietCongTacRest")
    public RestTemplate restTemplate;

    @Autowired
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper;

    private final String path = "";

    public ChiTietCongTacDTO getById(Integer id) {

        String restURL = path + "/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<ChiTietCongTacDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<ChiTietCongTacDTO>() {
                });

        return responseEntity.getBody();
    }

    public List<ChiTietCongTacDTO> getAll() throws IOException {

        String restURL = path;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);

        try {

            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<ChiTietCongTacDTO>>() {});
        } catch (Exception e) {

            return null;
        }
    }

    public List<ChiTietCongTacDTO> getByCongTac(Integer id) throws IOException {

        String restURL = path + "/congtac/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);

        try {

            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<ChiTietCongTacDTO>>() {});
        } catch (Exception e) {

            return null;
        }
    }

    public boolean add(ChiTietCongTacDTO chiTietCongTacDTO) {

        String restURL = path + "/add";
        HttpHeaders headers = new HttpHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<ChiTietCongTacDTO> entity = new HttpEntity<>(chiTietCongTacDTO, headers);

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

    public boolean dongY(Integer id) {
        String restURL = path + "/dongy/" + id;
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }

    public boolean tuChoi(Integer id) {
        String restURL = path + "/tuchoi/" + id;
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }

    public boolean daTaoChiPhi(Integer id) {
        String restURL = path + "/dataochiphi/" + id;
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }

}
