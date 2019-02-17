package cinemaModule.service;

public interface CinemaVipService {

	void totalSet(Integer levelCatalogy);

	void eachLevelSet(Integer level, Float discount);

	void ChangeLevelSet(Integer level, Float discount);

	void changeCustomLevel(Integer customNumb, Integer newLevel);

}
