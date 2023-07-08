package com.toolclass;

public class abc {
	private String memberNo;
	private String myMotions;
	private String myMotionType;
	private String myMuscles0;
	private String myMuscles1;



		public String getMemberNo() {
			return memberNo;
		}

		public void setMemberNo(String memberNo) {
			this.memberNo = memberNo;
		}

		public String getMyMotions() {
			return myMotions;
		}

		public void setMyMotions(String myMotions) {
			this.myMotions = myMotions;
		}

		public String getMyMotionType() {
			return myMotionType;
		}

		public void setMyMotionType(String myMotionType) {
			this.myMotionType = myMotionType;
		}

		public String getMyMuscles0() {
			return myMuscles0;
		}

		public void setMyMuscles0(String myMuscles0) {
			this.myMuscles0 = myMuscles0;
		}

		public String getMyMuscles1() {
			return myMuscles1;
		}

		public void setMyMuscles1(String myMuscles1) {
			this.myMuscles1 = myMuscles1;
		}

		public abc(String memberNo, String myMotions, String myMotionType, String myMuscles0, String myMuscles1) {
			super();
			this.memberNo = memberNo;
			this.myMotions = myMotions;
			this.myMotionType = myMotionType;
			this.myMuscles0 = myMuscles0;
			this.myMuscles1 = myMuscles1;
		}
		

}
