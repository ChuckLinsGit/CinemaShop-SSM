package cinemaModule.dao;

public interface CinemaVipDao {

	void addVipLevel(Integer levelCatalogy);

	void modifyLevelSet(Integer level, Float discount);

	void changeCustomLevel(Integer customNumb, Integer newLevel);

}
