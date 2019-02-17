package cinemaModule.service;

import cinemaModule.dao.CinemaVipDao;

public class CinemaVipServiceImpl implements CinemaVipService {

	private CinemaVipDao cinemaVipDao;

	@Override
	public void totalSet(Integer levelCatalogy) {
		cinemaVipDao.addVipLevel(levelCatalogy);
	}


	@Override
	public void eachLevelSet(Integer level, Float discount) {
		cinemaVipDao.modifyLevelSet(level,discount);
	}

	@Override
	public void ChangeLevelSet(Integer level, Float discount) {
		cinemaVipDao.modifyLevelSet(level,discount);
	}

	@Override
	public void changeCustomLevel(Integer customNumb, Integer newLevel) {
		cinemaVipDao.changeCustomLevel(customNumb,newLevel);
	}

}
