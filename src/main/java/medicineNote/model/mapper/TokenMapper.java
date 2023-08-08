package medicineNote.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import medicineNote.model.domain.Token;

@Mapper
public interface TokenMapper {
	
	@Select(value="SELECT * FROM token")
	Token find();
	
	@Update(value="UPDATE token SET token = #{token},updated_at = now()")
	int updateToken(String token);
}
