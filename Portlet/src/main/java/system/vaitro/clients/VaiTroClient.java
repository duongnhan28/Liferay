package system.vaitro.clients;

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
import system.vaitro.dto.VaiTroDTO;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaiTroClient {

    private final String path = "";
    @Autowired
    @Qualifier("vaiTroRest")
    public RestTemplate restTemplate;

    @Autowired
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper;

    public VaiTroDTO getVaiTro(Integer id) {

        String restURL = path + "/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<VaiTroDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<VaiTroDTO>() {
                });

        return responseEntity.getBody();
    }

    public List<VaiTroDTO> getVaiTros() throws IOException {

        String restURL = path;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);
        try {

            return objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<List<VaiTroDTO>>() {});
        } catch (Exception e) {

            return null;
        }
    }

    public boolean addVaiTro(VaiTroDTO chucVuDTO) {

        String restURL = path + "/add";
        HttpHeaders headers = new HttpHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<VaiTroDTO> entity = new HttpEntity<>(chucVuDTO, headers);

        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
                entity, String.class);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }

    public boolean deleteVaiTro(Integer id) {

        String restURL = path + "/delete/" + id;
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, entity, String.class);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();

    }
}

