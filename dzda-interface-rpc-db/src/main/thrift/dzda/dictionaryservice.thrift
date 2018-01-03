include "dzda/dictionary.thrift"

namespace java com.igool.rpc.db.service.thrift

	service DictionaryService {
		 	list<dictionary.Dictionary> findAllDictionary()
	}