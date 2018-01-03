namespace java com.igool.queue.service.thrift
	service DistributeQueueservice {
    		 	void addQueueStr(1:string key, 2:string jsonStr),
    		 	list<string> getQueueListStr(1:string key)
    	}