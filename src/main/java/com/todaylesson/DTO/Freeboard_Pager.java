package com.todaylesson.DTO;


public class Freeboard_Pager {
//Freeboard 페이징처리
	
	public static final int PAGE_SCALE = 15;
	public static final int BLOCK_SCALE = 5;
	
	private int totalPage;
	private int curPage;
	private int prevPage;
	private int nextPage;
	private int totalBlock;
	private int curBlock;
	private int prevBlock;
	private int nextBlock;
	
	private int pageBegin;
	private int pageEnd;
	
	private int blockBegin;
	private int blockEnd;
	
	
	
	//생성자
	public Freeboard_Pager(int count, int curPage)
	{
		curBlock=1;
		this.curPage= curPage;
		setTotalPage(count); //전체 페이지 갯수 계산
		setPageRange();
		setTotalBlock(); //전체 페이지 블록 갯수 계산
		setBlockRange(); // 페이지 블록의 시작, 끝 번호 계산
	}

	public void setBlockRange() {
		// 현재 페이지가 몇번째 페이지 블록에 속하는 지 계산
		curBlock = (int)Math.ceil((curPage-1) / BLOCK_SCALE)+1;
		
		//현재 페이지 블록의 시작, 끝 번호 계산
		blockBegin = (curBlock-1)*BLOCK_SCALE+1;
		
		//페이지 블록의 끝번호
		blockEnd = blockBegin+BLOCK_SCALE-1;
		
		//마지막 블록이 범위를 초과하지 않도록 계산
		if(blockEnd > totalPage) blockEnd = totalPage;
		
		// 이전,다음을 눌렀을 때 이동할 페이지 번호
		prevPage = (curPage ==1)? 1:(curBlock-1)*BLOCK_SCALE;
		nextPage = curBlock > totalBlock ? (curBlock * BLOCK_SCALE) : (curBlock*BLOCK_SCALE);
		
		//마지막 페이지가 범위를 초과하지 않도록 처리
		if(nextPage >= totalPage) nextPage = totalPage;
	}
	
	public void setPageRange() {
		
		//시작번호 = (현재페이지 -1) * 페이지당 게시물 수 + 1
		pageBegin = (curPage-1)*PAGE_SCALE+1;
		
		//끝번호 = 시작번호 + 페이지당 게시물 수 -1
		pageEnd = pageBegin+PAGE_SCALE-1;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int count) {
		totalPage = (int)Math.ceil(count*1.0/PAGE_SCALE);
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock() {
		totalBlock = (int)Math.ceil(totalPage / BLOCK_SCALE);
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}
	
}