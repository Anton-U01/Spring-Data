package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TaskRootDto;
import softuni.exam.models.dto.TaskSeedDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.models.entity.Task;
import softuni.exam.repository.CarsRepository;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.repository.PartsRepository;
import softuni.exam.repository.TasksRepository;
import softuni.exam.service.TasksService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class TasksServiceImpl implements TasksService {
    private static String TASKS_FILE_PATH = "src/main/resources/files/xml/tasks.xml";
    private final TasksRepository taskRepository;
    private final MechanicsRepository mechanicsRepository;
    private final PartsRepository partsRepository;
    private final CarsRepository carsRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public TasksServiceImpl(TasksRepository taskRepository, MechanicsRepository mechanicsRepository, PartsRepository partsRepository, CarsRepository carsRepository, ModelMapper mapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.taskRepository = taskRepository;
        this.mechanicsRepository = mechanicsRepository;
        this.partsRepository = partsRepository;
        this.carsRepository = carsRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(TASKS_FILE_PATH)));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();
        TaskRootDto rootDto = xmlParser.parse(TaskRootDto.class, TASKS_FILE_PATH);
        for (TaskSeedDto dto : rootDto.getTasks()) {
            Optional<Mechanic> optionalMechanic = mechanicsRepository.findByFirstName(dto.getMechanic().getFirstName());
            if(!validationUtil.isValid(dto) || optionalMechanic.isEmpty()){
                stringBuilder.append("Invalid task\n");
                continue;
            }
            Task task = mapper.map(dto, Task.class);
            task.setCar(carsRepository.getById(dto.getCar().getId()));
            task.setMechanic(optionalMechanic.get());
            task.setPart(partsRepository.getById(dto.getPart().getId()));
            taskRepository.saveAndFlush(task);
            stringBuilder.append(String.format("Successfully imported task %.2f\n",task.getPrice()));
        }

        return stringBuilder.toString();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        return null;
    }
}
