package com.ngleanhvu.job.domain.model.job;

import com.ngleanhvu.common.exception.DomainException;
import com.ngleanhvu.common.util.ValidationUtil;
import com.ngleanhvu.job.domain.model.job.enums.WorkplaceType;

public record WorkPlace(WorkplaceType workplaceType, Address address) {
  public WorkPlace {
    if ((workplaceType == WorkplaceType.HYBRID || workplaceType == WorkplaceType.ONSITE)
        && ValidationUtil.isNull(address)) {
      throw new DomainException("Please provide address for hybrid and onsite working");
    }
  }
}
