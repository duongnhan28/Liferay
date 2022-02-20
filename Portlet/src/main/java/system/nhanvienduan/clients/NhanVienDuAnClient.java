package system.nhanvienduan.clients;

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
import system.nhanvienduan.dto.NhanVienDuAnDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NhanVienDuAnClient {

    private final String path = "";

    @Autowired
    @Qualifier("nhanVienDuAnRest")
    public RestTemplate restTemplate;

    @Autowired
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper;

    public NhanVienDuAnDTO getNhanVienDuAn(Integer id) {

        String restURL = path + "/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<NhanVienDuAnDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<NhanVienDuAnDTO>() {
                });

        return responseEntity.getBody();
    }

    public List<NhanVienDuAnDTO> getNhanVienDuAns() {

        String restURL = path;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null , String.class);

        try {

            return objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<List<NhanVienDuAnDTO>>() {});
        } catch (Exception e) {

            return null;
        }
    }

    public List<NhanVienDuAnDTO> getNhanVienByDuAn(Integer id) {

        String restURL = path + "/duan/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null , String.class);

        try {

            return objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<List<NhanVienDuAnDTO>>() {});
        } catch (Exception e) {

            return null;
        }
    }

    public List<NhanVienDuAnDTO> getDuAnByNhanVien(Integer id) {

        String restURL = path + "/nhanvienduan/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null , String.class);

        try {

            return objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<List<NhanVienDuAnDTO>>() {});
        } catch (Exception e) {

            return null;
        }
    }

    public boolean addNhanVienDuAn(NhanVienDuAnDTO nhanVienDuAnDTO) {

        String restURL = path + "/add";
        HttpHeaders headers = new HttpHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<NhanVienDuAnDTO> entity = new HttpEntity<>(nhanVienDuAnDTO, headers);

        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
                entity, String.class);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }

    public boolean deleteNhanVienDuAn(Integer id) {

        String restURL = path + "/delete/" + id;
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, entity, String.class);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }

    public List<NhanVienDuAnDTO> getNhanVienDuAnByNhanVien(Integer id) {

        String restURL = path + "/nhanvien/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null , String.class);

        try {
            return objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<List<NhanVienDuAnDTO>>() {});
        } catch (Exception e) {
            return null;
        }
    }
}
