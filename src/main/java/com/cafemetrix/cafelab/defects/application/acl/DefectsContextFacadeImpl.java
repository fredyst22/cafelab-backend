package com.cafemetrix.cafelab.defects.application.acl;

import com.cafemetrix.cafelab.defects.domain.model.commands.CreateDefectCommand;
import com.cafemetrix.cafelab.defects.domain.model.queries.GetDefectsByCoffeeIdQuery;
import com.cafemetrix.cafelab.defects.domain.services.DefectCommandService;
import com.cafemetrix.cafelab.defects.domain.services.DefectQueryService;
import com.cafemetrix.cafelab.defects.interfaces.acl.DefectsContextFacade;
import com.cafemetrix.cafelab.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefectsContextFacadeImpl implements DefectsContextFacade {
    private final DefectCommandService defectCommandService;
    private final DefectQueryService defectQueryService;

    public DefectsContextFacadeImpl(DefectCommandService defectCommandService, DefectQueryService defectQueryService) {
        this.defectCommandService = defectCommandService;
        this.defectQueryService = defectQueryService;
    }

    @Override
    public Long createDefect(Long coffeeId, String name, String defectType, Double defectWeight,
                             Double percentage, String probableCause, String suggestedSolution) {
        var createDefectCommand = new CreateDefectCommand(coffeeId, name, defectType, defectWeight,
                percentage, probableCause, suggestedSolution);
        var defect = defectCommandService.handle(createDefectCommand);
        return defect.isEmpty() ? 0L : defect.get().getId();
    }

    @Override
    public List<Long> fetchDefectIdsByCoffeeId(Long coffeeId) {
        var getDefectsByCoffeeIdQuery = new GetDefectsByCoffeeIdQuery(coffeeId);
        var defects = defectQueryService.handle(getDefectsByCoffeeIdQuery);
        return defects.stream()
                .map(AuditableAbstractAggregateRoot::getId)
                .collect(Collectors.toList());
    }
}